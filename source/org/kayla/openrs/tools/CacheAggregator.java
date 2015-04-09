/**
 * Copyright (c) 2014 Launcher Studios
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kayla.openrs.tools;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;

import org.kayla.Constants;
import org.kayla.openrs.Container;
import org.kayla.openrs.FileStore;
import org.kayla.openrs.ReferenceTable;
import org.kayla.openrs.ReferenceTable.Entry;

/**
 * 
 * @author Im Frizzy <skype:kfriz1998>
 * @since Sep 18, 2014
 */
public final class CacheAggregator {

	public static void main(String[] args) throws IOException {
		FileStore otherStore = FileStore.open(Constants.CACHE_REPOSITORY);
		FileStore store = FileStore
				.open("C:/Users/Fricilone/Dropbox/Battle-Rune 751/BattleRune/data/cache/");

		for (int type = 0; type < store.getFileCount(255); type++) {
			if (type == 7)
				continue; // TODO need support for newer ref table format for
							// this index

			ReferenceTable otherTable = ReferenceTable.decode(Container.decode(
					otherStore.read(255, type)).getData());
			ReferenceTable table = ReferenceTable.decode(Container.decode(
					store.read(255, type)).getData());
			for (int file = 0; file < table.capacity(); file++) {
				Entry entry = table.getEntry(file);
				if (entry == null)
					continue;

				if (isRepackingRequired(store, entry, type, file)) {
					Entry otherEntry = otherTable.getEntry(file);
					if (entry.getVersion() == otherEntry.getVersion()
							&& entry.getCrc() == otherEntry.getCrc()) {
						store.write(type, file, otherStore.read(type, file));
					}
				}
			}
		}
	}

	private static boolean isRepackingRequired(FileStore store, Entry entry,
			int type, int file) {
		ByteBuffer buffer;
		try {
			buffer = store.read(type, file);
		} catch (IOException ex) {
			return true;
		}

		if (buffer.capacity() <= 2) {
			return true;
		}

		byte[] bytes = new byte[buffer.limit() - 2]; // last two bytes are the
														// version and shouldn't
														// be included
		buffer.position(0);
		buffer.get(bytes, 0, bytes.length);

		CRC32 crc = new CRC32();
		crc.update(bytes, 0, bytes.length);

		if ((int) crc.getValue() != entry.getCrc()) {
			return true;
		}

		buffer.position(buffer.limit() - 2);
		if ((buffer.getShort() & 0xFFFF) != entry.getVersion()) {
			return true;
		}

		return false;
	}

}

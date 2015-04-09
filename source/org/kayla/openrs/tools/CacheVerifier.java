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
public final class CacheVerifier {

	public static void main(String[] args) {
		try (FileStore store = FileStore.open(Constants.CACHE_REPOSITORY)) {
			double totalCompletion = 0;
			double totalFiles = 0;
			double filesHave = 0;
			for (int type = 0; type < store.getFileCount(255); type++) {
				double succesfullCount = 0;
				totalFiles += store.getFileCount(type);

				if (store.getFileCount(type) == 0)
					continue;

				System.out.println("Checking index: " + type
						+ ", Total Files: " + store.getFileCount(type));
				ReferenceTable table = ReferenceTable.decode(Container.decode(
						store.read(255, type)).getData());
				for (int file = 0; file < table.capacity(); file++) {
					if (type == 14 && file == 0)
						continue;

					Entry entry = table.getEntry(file);
					if (entry == null)
						continue;

					ByteBuffer buffer;
					try {
						buffer = store.read(type, file);
						succesfullCount++;
						filesHave++;
					} catch (IOException ex) {
						System.out.println("index: " + type + ", file: " + file
								+ " error");
						continue;
					}

					double completion = succesfullCount
							/ store.getFileCount(type) * 100;
					totalCompletion += completion;

					try {
						if (buffer.capacity() <= 2) {
							System.out.println("index: " + type + ", file: "
									+ file + " missing");
							continue;
						}
					} catch (NullPointerException ex) {
						continue;
					}

					byte[] bytes = new byte[buffer.limit() - 2];
					buffer.position(0);
					buffer.get(bytes, 0, bytes.length);

					CRC32 crc = new CRC32();
					crc.update(bytes, 0, bytes.length);

					if ((int) crc.getValue() != entry.getCrc()) {
						System.out.println("index: " + type + ", file: " + file
								+ " corrupt");
					}

					buffer.position(buffer.limit() - 2);
					int version = (buffer.getShort() & 0xFFFF);
					if (version != (entry.getVersion() & 0xFFFF)) {
						System.out.println("index: " + type + ", file: " + file
								+ " out of date");
					}
				}
			}
			int completion = (int) ((totalCompletion / store.getTypeCount()) * 100);
			completion = (int) (((filesHave / totalFiles) * 100) * 100);
			System.out.println("Have " + filesHave + " out of " + totalFiles);
			System.out.println((double) completion / 100 + "%");
			store.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

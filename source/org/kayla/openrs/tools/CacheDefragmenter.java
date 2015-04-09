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
import java.util.concurrent.TimeUnit;

import org.kayla.Constants;
import org.kayla.openrs.Container;
import org.kayla.openrs.FileStore;
import org.kayla.openrs.ReferenceTable;

/**
 * 
 * @author Im Frizzy <skype:kfriz1998>
 * @since Sep 18, 2014
 */
public final class CacheDefragmenter {

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		try (FileStore in = FileStore.open(Constants.CACHE_REPOSITORY)) {
			try (FileStore out = FileStore.create(
					System.getProperty("user.home") + "/Desktop/data822-de/",
					in.getTypeCount())) {
				for (int type = 0; type < in.getTypeCount(); type++) {
					if (in.getFileCount(type) == 0)
						continue;

					ByteBuffer buf = in.read(255, type);
					buf.mark();
					out.write(255, type, buf);
					buf.reset();

					ReferenceTable rt = ReferenceTable.decode(Container.decode(
							buf).getData());
					for (int file = 0; file < rt.capacity(); file++) {
						if (rt.getEntry(file) == null)
							continue;

						out.write(type, file, in.read(type, file));
					}
				}
				out.close();
			}
			in.close();
		}
		System.out.println("Took: "
				+ TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()
						- start));
	}

}

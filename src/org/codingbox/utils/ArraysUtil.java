package org.codingbox.utils;

import java.lang.reflect.Array;

/**
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class ArraysUtil {
  public static <T> T[] arrayMerge(T[]... arrays) {
    int count = 0;
    for (T[] array : arrays) {
      count += array.length;
    }

    T[] mergedArray = (T[]) Array.newInstance(arrays[0][0].getClass(), count);

    int start = 0;
    for (T[] array : arrays) {
      System.arraycopy(array, 0, mergedArray, start, array.length);
      start += array.length;
    }

    return (T[]) mergedArray;
  }
}

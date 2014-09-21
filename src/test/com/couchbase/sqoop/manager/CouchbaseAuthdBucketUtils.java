/**
 * Copyright 2011 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.couchbase.sqoop.manager;

import com.cloudera.sqoop.SqoopOptions;

/**
 * Holds test configuration information.
 */
public final class CouchbaseAuthdBucketUtils {
  public static final String CONNECT_STRING =
      System.getProperty("sqoop.test.couchbase.connectstring",
      "http://localhost:8091/pools");

  public static final String COUCHBASE_USER_NAME = "named";

  public static final String COUCHBASE_USER_PASS = "letmein";

  public static final String COUCHBASE_CONN_MANAGER =
      "com.couchbase.sqoop.manager.CouchbaseManager";

  private CouchbaseAuthdBucketUtils() {
    // Empty
  }

  public static void setCouchbaseAuth(SqoopOptions options) {
    options.setUsername(COUCHBASE_USER_NAME);
    options.setPassword(COUCHBASE_USER_PASS);
  }
}

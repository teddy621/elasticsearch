/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.common.xcontent.smile;

import org.elasticsearch.common.io.FastByteArrayOutputStream;
import org.elasticsearch.common.jackson.JsonGenerator;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContentGenerator;
import org.elasticsearch.common.xcontent.support.XContentMapConverter;

import java.io.IOException;

/**
 * @author kimchy (shay.banon)
 */
public class SmileXContentGenerator extends JsonXContentGenerator {

    public SmileXContentGenerator(JsonGenerator generator) {
        super(generator);
    }

    @Override public XContentType contentType() {
        return XContentType.SMILE;
    }

    @Override public void writeRawField(String fieldName, byte[] content, FastByteArrayOutputStream bos) throws IOException {
        writeFieldName(fieldName);
        // crap here, need to find a better way... (at least don't create the map and direct parser to generator)
        XContentMapConverter.writeMap(this, SmileXContent.smileXContent.createParser(content).map());
    }
}
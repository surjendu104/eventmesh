/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.eventmesh.connector.openfunction.source.connector;

import org.apache.eventmesh.connector.openfunction.source.config.OpenFunctionSourceConfig;
import org.apache.eventmesh.openconnect.api.config.Config;
import org.apache.eventmesh.openconnect.api.data.ConnectRecord;
import org.apache.eventmesh.openconnect.api.source.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenFunctionSourceConnector implements Source {

    private OpenFunctionSourceConfig sourceConfig;

    private BlockingQueue<ConnectRecord> queue;

    @Override
    public Class<? extends Config> configClass() {
        return OpenFunctionSourceConfig.class;
    }

    @Override
    public void init(Config config) throws Exception {
        // init config for openfunction source connector
        this.sourceConfig = (OpenFunctionSourceConfig) config;
        this.queue = new LinkedBlockingQueue<>(1000);
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void commit(ConnectRecord record) {

    }

    @Override
    public String name() {
        return this.sourceConfig.getSourceConnectorConfig().getConnectorName();
    }

    @Override
    public void stop() {

    }

    public BlockingQueue<ConnectRecord> queue() {
        return queue;
    }

    @Override
    public List<ConnectRecord> poll() {
        List<ConnectRecord> connectRecords = new ArrayList<>();
        ConnectRecord connectRecord = queue.poll();
        if (connectRecord != null) {
            connectRecords.add(connectRecord);
        }
        return connectRecords;
    }
}
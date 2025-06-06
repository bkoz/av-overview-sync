/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.examples;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public record ApplicationConfiguration(
  BatchIngest batchIngest,
  File file,
  S3 s3,
  Poller poller,
  AlphaVantage alphaVantage,
  Weaviate weaviate) {

  public record BatchIngest(
    boolean enabled,
    IngestType type) {

    enum IngestType {
      EMBEDDED,
      FILE,
      S3
    }
  }

  public record Poller(
    boolean enabled,
    Set<String> symbols,
    long period) {

  }

  public record File(
    String directory,
    String fileName,
    boolean watch,
    long watchPeriod,
    boolean update) {

  }

  public record S3(
    String accessKey,
    String secretKey,
    String bucketName,
    String regionName,
    String fileName,
    boolean watch,
    long watchPeriod,
    boolean update) {

  }

  public record AlphaVantage(
    String scheme,
    String host,
    int port,
    String path,
    String function,
    String apiKey,
    boolean throttleEnabled,
    long throttleRequests,
    long throttlePeriod) {

  }

  public record Weaviate(
    String scheme,
    String host,
    int port,
    boolean grpcSecured,
    String grpcHost,
    int grpcPort,
    String apiKey,
    Map<String, String> headers,
    Schema schema,
    boolean throttleEnabled,
    long throttleRequests,
    long throttlePeriod) {

    public record Schema(
      boolean initialize,
      boolean dropIfExists,
      String name,
      List<Property> properties,
      String vectorizerModule,
      Map<String, Object> vectorizerModuleConfig,
      String generativeModule,
      Map<String, Object> generativeModuleConfig) {
      
      public record Property(
        String name,
        List<String> dataTypes) {
        
      }
    }
  }
}

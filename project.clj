(defproject aws-clojure-simple-http-endpoint "0.1.0-SNAPSHOT"
  :description "This example demonstrates how to setup a simple HTTP GET endpoint using Clojure. Once you ping it, it will reply with the current time."
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-time "0.15.1"]
                 [com.amazonaws/aws-lambda-java-core "1.2.0"]
                 [com.amazonaws/aws-lambda-java-events "2.2.5"]]
  :uberjar-name "aws-clojure-simple-http-endpoint.jar"
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

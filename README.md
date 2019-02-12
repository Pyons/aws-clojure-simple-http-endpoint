# Simple HTTP Endpoint Example

This example demonstrates how to setup a simple HTTP GET endpoint using Clojure. Once you ping it, it will reply with the current time.

Based on: [aws-java-simple-http-endpoint](https://github.com/serverless/examples/tree/master/aws-java-simple-http-endpoint)

## Use Cases

- Wrapping an existing internal or external endpoint/service

## Build

It is required to build prior to deploying. You can build the deployment artifact using [Leiningen](https://leiningen.org/).

```bash
lein uberjar
```
Resulting uberjar is located at: `target/uberjar/aws-clojure-simple-http-endpoint.jar`

```yaml
package:
  artifact: target/uberjar/aws-clojure-simple-http-endpoint.jar
```


## Deploy

Change the region inside the `serverless.yml` to match your AWS region.

```yaml
provider:
  ...
  region: eu-central-1
```

After having built the deployment artifact using Leiningen you can deploy by simply running

```bash
serverless deploy
```

## Usage

You can now invoke the Lambda function directly and see the resulting log via

```bash
serverless invoke -f current-time --log
```

The expected result should be similar to:

```bash
{
    "statusCode": 200,
    "body": "{\"message\":\"Hello, the current time is Wed Jan 04 23:44:37 UTC 2017\"}",
    "headers": {
        "Access-Control-Allow-Origin": "*"
        "Content-Type": "application/json"
    }
}
--------------------------------------------------------------------
START RequestId: XXXXXXX Version: $LATEST
2019 23:44:37 <XXXXXXX> INFO  com.serverless.Handler:18 - received: {}
END RequestId: XXXXXXX
REPORT RequestId: XXXXXXX	Duration: 0.51 ms	Billed Duration: 100 ms 	Memory Size: 256 MB	Max Memory Used: 53 MB
```

Finally you can send an HTTP request directly to the endpoint using a tool like curl

```bash
curl https://XXXXXXX.execute-api.{REGION}.amazonaws.com/dev/ping
```

The expected result should be similar to:

```bash
{"message": "Hello, the current time is Wed Jan 04 23:44:37 +0000"}%  
```

## Scaling

By default, AWS Lambda limits the total concurrent executions across all functions within a given region to 100. The default limit is a safety limit that protects you from costs due to potential runaway or recursive functions during initial development and testing. To increase this limit above the default, follow the steps in [To request a limit increase for concurrent executions](http://docs.aws.amazon.com/lambda/latest/dg/concurrent-executions.html#increase-concurrent-executions-limit).

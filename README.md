# Clojure Template

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

```shell
lein ring server
```

## check code style

```shell
lein bikeshed -v
```

## Test

### test with auto refresh

```shell
lein test-refresh
```

### run with profile

```shell
lein with-profile dev,default test-refresh
```

### run single namespace

```shell
lein test :only namespace_name
```

### run single test in namespace

```shell
lein test :only namespace_name/testname
```

## mutaton

```shell
echo 'mutation {add_course(name: "Rugal") {id name} }' | http POST localhost:8080/graphql
```

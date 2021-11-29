(defproject clojure-template "1.0.0-SNAPSHOT"
  :description "A generic template for clojure"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [compojure "1.6.2"]
                 ;ring
                 [ring/ring-jetty-adapter "1.9.4"]
                 [ring/ring-defaults "0.3.3"]
                 [ring/ring-json "0.5.1"]
                 [ring-json-response "0.2.0"]
                 ;database
                 [org.postgresql/postgresql "42.2.24"]
                 ;ORM
                 [korma "0.4.3"]
                 ;nrepl
                 [nrepl/lein-nrepl "0.3.2"]
                 ;graphql
                 [com.walmartlabs/lacinia-pedestal "0.16.1"]
                 [io.aviso/logging "1.0"]
                 [integrant "0.8.0"]
                 [environ "1.2.0"]]
  :plugins [[lein-ring "0.12.5"]
            [lein-bikeshed "0.5.2"]
            [lein-environ "1.2.0"]
            [lein-pprint "1.3.2"]
            [lein-cloverage "1.2.2"]
            [com.github.metaphor/lein-flyway "6.0.0"]
            [com.jakemccrary/lein-test-refresh "0.25.0"]]
  :main ^:skip-aot ga.rugal.clojure.main
  :ring {:handler       ga.rugal.clojure.main/-main
         :auto-reload?  true
         :auto-refresh? true}
  :test-refresh {:quiet true}
  :flyway {:config-path #=(eval (or (System/getenv "flyway_config") "resources/database/flyway.properties"))}
  :bikeshed {:var-redefs           false
             :trailing-blank-lines false
             :max-line-length      100
             :name-collisions      false}
  :cloverage {:junit?         true
              :fail-threshold 90}
  :uberjar-name "clojure-standalone.jar"
  :profiles {:default     {:env {:subname "//localhost:5432/postgres" :username "postgres" :password "123"}}
             :ci          {:env {:subname "//localhost:5432/postgres" :username "postgres" :password "123"}}
             :development {:env {:subname  "//ec2-3-91-127-228.compute-1.amazonaws.com:5432/d6qbksja2ontqr?sslmode=require"
                                 :username "mjhosjkhlmtwbr"
                                 :password "c493b1e6d8d3b968686b024f6e1ea4f9b5fb5d9a908de2c9e9cebfc4a6b1986f"}}
             :uberjar     {:aot :all}
             :production  {}})

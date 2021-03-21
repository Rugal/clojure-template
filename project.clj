(defproject hello-world "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 ;ring
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [ring-json-response "0.2.0"]
                 ;database
                 [org.postgresql/postgresql "42.2.2.jre7"]
                 ;ORM
                 [korma "0.4.3"]
                 [environ "1.2.0"]]
  :plugins [[lein-ring "0.12.5"]
            [lein-bikeshed "0.5.1"]
            [lein-environ "1.1.0"]
            [lein-cprint "1.3.3"]
            [lein-pprint "1.3.2"]
            [lein-cloverage "1.2.2"]
            [com.github.metaphor/lein-flyway "6.0.0"]
            [com.jakemccrary/lein-test-refresh "0.23.0"]]
  :ring {:handler       ga.rugal.clojure.main/main
         :auto-reload?  true
         :auto-refresh? true}
  :test-refresh {:quiet true}
  :flyway {:config-path "resources/database/flyway.properties"}
  :bikeshed {:var-redefs           false
             :trailing-blank-lines false
             :max-line-length      100
             :name-collisions      false}
  :cloverage {:junit? true}
  :profiles {:default     {:env {:subname "//localhost:5432/postgres" :username "postgres" :password "123"}}
             :ci          {:env {:subname "//localhost:5432/postgres" :username "postgres" :password "123"}}
             :development {}
             :production  {}})

(ns ga.rugal.clojure.schema
  "Contains custom resolvers and a function to provide the full schema."
  (:require
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [com.walmartlabs.lacinia :as l]
   [com.walmartlabs.lacinia.schema :as schema]
   [com.walmartlabs.lacinia.util :as util]
   [ga.rugal.clojure.graphql.index :as i]))

(defn- load-schema
  []
  (-> (io/resource "schema.edn")
      slurp
      edn/read-string
      (util/attach-resolvers (i/resolver-map))
      schema/compile))

(defn q
  [query-string]
  (l/execute (load-schema) query-string nil nil))

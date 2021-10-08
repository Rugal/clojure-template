(ns ga.rugal.clojure.schema
  "Contains custom resolvers and a function to provide the full schema."
  (:require
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [ga.rugal.clojure.core.service.course :as s]
   [com.walmartlabs.lacinia :as l]
   [com.walmartlabs.lacinia.schema :as schema]
   [com.walmartlabs.lacinia.util :as util]))

(defn- resolver-map
  []
  {:query/get-course
   (fn [context args value] (let [{:keys [id]} args id (Integer. id)] (s/get id)))})

(defn- load-schema
  []
  (-> (io/resource "schema.edn")
      slurp
      edn/read-string
      (util/attach-resolvers (resolver-map))
      schema/compile))

(defn q
  [query-string]
  (l/execute (load-schema) query-string nil nil))

(ns ga.rugal.clojure.controller.graphql.index
  "Graphql resolver aggregator"
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [com.walmartlabs.lacinia :as l]
   [com.walmartlabs.lacinia.schema :as schema]
   [com.walmartlabs.lacinia.util :as util]
   [ga.rugal.clojure.controller.graphql.course :refer :all]
   [ga.rugal.clojure.controller.graphql.registration :refer :all]
   [ga.rugal.clojure.controller.graphql.student :refer :all]
   [integrant.core :as ig]))

(defmethod ig/init-key :graphql/executor [_ {:keys [schema resolver]}]
  (fn [query-string]
    (l/execute
     (-> (io/resource schema)
         slurp
         edn/read-string
         (util/attach-resolvers (resolver))
         schema/compile)
     query-string
     nil
     nil)))

(defmethod ig/init-key :graphql/resolver [_ {:keys [student course registration]}]
  (fn [] (merge {} student course registration)))

(ns ga.rugal.clojure.graphql.student
  "Contains student related resolver."
  (:require
   [ga.rugal.clojure.core.service.student :as s]))

(def resolver
  {:query/student (fn [context args value] (let [{:keys [id]} args id (Integer. id)] (s/get id)))})

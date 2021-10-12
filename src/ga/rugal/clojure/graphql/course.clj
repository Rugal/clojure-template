(ns ga.rugal.clojure.graphql.course
  "Contains course related resolver."
  (:require
   [ga.rugal.clojure.core.service.course :as s]))

(def resolver
  {:query/course (fn [context args value] (let [{:keys [id]} args id (Integer. id)] (s/get id)))})

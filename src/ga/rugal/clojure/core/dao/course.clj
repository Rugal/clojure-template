(ns ga.rugal.clojure.core.dao.course
  "namespace for course dao"
  (:require
   [korma.core :as korma]
   [ga.rugal.clojure.core.model.entity :refer [course]]))

(defn get
  "get course by id"
  [id]
  (first
   (korma/select course
                 (korma/where {:id id}))))

(defn save
  "save course"
  [bean]
  (korma/insert course
                (korma/values {:name (:name bean)})))

(defn update
  "update course"
  [bean]
  (korma/update course
                (korma/set-fields {:name (:name bean)})
                (korma/where {:id (:id bean)})))

(defn delete
  "delete course"
  [id]
  (korma/delete course
                (korma/where {:id id})))

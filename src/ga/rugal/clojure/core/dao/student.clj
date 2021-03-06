(ns ga.rugal.clojure.core.dao.student
  "namespace for student dao"
  (:require [korma.core :as korma])
  (:require [ga.rugal.clojure.core.model.entity :refer :all]))

(defn get
  "get student by id"
  [id]
  (first
    (korma/select student
                  (korma/fields :id :name :age)
                  (korma/where {:id id}))))

(defn save
  "save student"
  [bean]
  (korma/insert student
                (korma/values {:name (:name bean)
                               :age  (:age bean)})))

(defn update
  "update student"
  [bean]
  (korma/update student
                (korma/set-fields {:name (:name bean)
                                   :age  (:age bean)})
                (korma/where {:id (:id bean)})))

(defn delete
  "delete student"
  [id]
  (korma/delete student
                (korma/where {:id id})))

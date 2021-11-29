(ns ga.rugal.clojure.core.dao.student
  "namespace for student dao"
  (:require
   [ga.rugal.clojure.core.model.db :refer :all]
   [ga.rugal.clojure.core.model.entity :refer [student]]
   [integrant.core :as ig]
   [korma.core :as korma]))

(defmethod ig/init-key :dao/student:get [_ {:keys [db]}]
  (fn [id] (first (korma/select student (korma/where {:id id})))))

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

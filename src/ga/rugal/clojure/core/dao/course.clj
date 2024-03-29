(ns ga.rugal.clojure.core.dao.course
  "namespace for course dao"
  (:require
   [ga.rugal.clojure.core.model.db :refer :all]
   [ga.rugal.clojure.core.model.entity :refer [course]]
   [integrant.core :as ig]
   [korma.core :as korma]))

(defmethod ig/init-key :dao/course:get [_ {:keys [db]}]
  (fn [id] (first (korma/select course (korma/where {:id id})))))

(defmethod ig/init-key :dao/course:post [_ {:keys [db]}]
  (fn [bean] (korma/insert course (korma/values {:name (:name bean)}))))

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

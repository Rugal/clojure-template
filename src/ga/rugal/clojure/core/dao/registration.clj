(ns ga.rugal.clojure.core.dao.registration
  "namespace for registration dao"
  (:require
   [korma.core :as korma]
   [ga.rugal.clojure.core.model.entity :refer [registration]]))

(defn get
  "get registration by id"
  [id]
  (first
   (korma/select registration
                 (korma/where {:id id}))))

(defn save
  "save registration"
  [bean]
  (korma/insert registration
                (korma/values {:student_id (:student_id bean)
                               :course_id  (:course_id bean)
                               :score      (:score bean)})))

(defn update
  "update registration"
  [bean]
  (korma/update registration
                (korma/set-fields
                 {:student_id (:student_id bean)
                  :score      (:score bean)
                  :course_id  (:course_id bean)})
                (korma/where {:id (:id bean)})))

(defn delete
  "delete registration"
  [id]
  (korma/delete registration
                (korma/where {:id id})))

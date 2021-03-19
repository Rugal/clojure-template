(ns ga.rugal.clojure.core.dao.registration
  "namespace for registration dao"
  (:require [korma.core :as korma])
  (:require [ga.rugal.clojure.core.model.entity :refer :all])
  (:require [ga.rugal.clojure.core.dao.student :as student])
  (:require [ga.rugal.clojure.core.dao.course :as course]))

(defn get-by-id
  "get registration by id"
  [id]
  (first
    (korma/select registration
      (korma/fields :id :score :student_id :course_id)
      (korma/where {:id id}))))

(defn get-full-by-id
  "get full registration by id"
  [id]
  (if-let [r (get-by-id id)]
    (-> r
      (assoc
        :student (student/get-by-id (:student_id r))
        :course (course/get (:course_id r)))
      (dissoc :course_id :student_id))))

(defn save
  "save registration"
  [bean]
  (if bean
    (korma/insert registration
      (korma/values {:student_id (:student_id bean)
                     :course_id (:course_id bean)
                     :score (:score bean)}))))

(defn update
  "update registration"
  [bean]
  (if bean
    (korma/update registration
      (korma/set-fields
        {:student_id (:student_id bean)
         :score (:score bean)
         :course_id (:course_id bean)})
      (korma/where {:id (:id bean)}))))

(defn delete
  "delete registration"
  [id]
  (if (get-by-id id)
    (korma/delete registration
      (korma/where {:id id}))))

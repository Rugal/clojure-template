(ns ga.rugal.clojure.core.service.registration
  "namespace for registration service"
  (:require [ga.rugal.clojure.core.dao.registration :as dao]
            [ga.rugal.clojure.core.service.student :as s]
            [ga.rugal.clojure.core.service.course :as c]))

(defn get
  "get registration by id"
  [id]
  (if-let [r (dao/get id)]
    (-> r
        (assoc
         :student (s/get (:student_id r))
         :course (c/get (:course_id r)))
        (dissoc :course_id :student_id))))

(defn save
  "save"
  [bean]
  (if (and (:student_id bean) (:course_id bean) (:score bean))
    (if (and (s/get (:student_id bean)) (c/get (:course_id bean)))
      (dao/save bean)
      (throw (ex-info "dependency not found" {:status 404})))))

(defn update
  "update"
  [bean]
  (if (and (:id bean) (:student_id bean) (:course_id bean) (:score bean))
    (if (and (dao/get (:id bean)) (s/get (:student_id bean)) (c/get (:course_id bean)))
      (dao/update bean)
      (throw (ex-info "dependency not found" {:status 404})))))

(defn delete
  "delete"
  [id]
  (if id
    (dao/delete id)))

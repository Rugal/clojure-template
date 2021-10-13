(ns ga.rugal.clojure.core.model.entity
  "namespace for entity definition"
  (:require [korma.core :refer [defentity table pk belongs-to has-many entity-fields]]
            [ga.rugal.clojure.core.model.db :refer :all]))

(declare student course registration)

(defentity student
  (table :school.student)
  (pk :id)
  (entity-fields :id :name :age)
  (has-many registration {:fk :id}))

(defentity course
  (table :school.course)
  (pk :id)
  (entity-fields :id :name)
  (has-many registration {:fk :id}))

(defentity registration
  (table :school.registration)
  (pk :id)
  (entity-fields :id :score :student_id :course_id)
  (belongs-to student)
  (belongs-to course))


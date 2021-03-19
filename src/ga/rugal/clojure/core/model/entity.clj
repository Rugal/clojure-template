(ns ga.rugal.clojure.core.model.entity
  "namespace for entity definition"
  (:require [korma.core :as korma])
  (:require [ga.rugal.clojure.core.model.db :refer :all]))

(declare student course registration)

(korma/defentity student
                 (korma/table :school.student)
                 (korma/pk :id)
                 ;(entity-fields :rid :address :name :phone)
                 (korma/has-many registration {:fk :id}))

(korma/defentity course
                 (korma/table :school.course)
                 (korma/pk :id)
                 (korma/has-many registration {:fk :id}))

(korma/defentity registration
                 (korma/table :school.registration)
                 (korma/pk :id)
                 (korma/belongs-to student {:fk :id})
                 (korma/belongs-to course {:fk :id}))


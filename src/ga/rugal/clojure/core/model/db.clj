(ns ga.rugal.clojure.core.model.db
  "Namespace for primary database connection configuration"
  (:require
   [korma.db :refer [defdb]]
   [environ.core :refer [env]]))

(def dbinfo
  "database connection definition"
  {:classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname     (env :subname)
   ; Any additional keys are passed to the driver
   ; as driver-specific properties.
   :user        (env :username)
   :password    (env :password)})

(defdb connection dbinfo)


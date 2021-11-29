(ns ga.rugal.clojure.core.model.db
  "Namespace for primary database connection configuration"
  (:require
   [korma.db :refer [defdb]]
   [integrant.core :as ig]
   [environ.core :refer [env]]))

(defmethod ig/init-key :database/connection [_ _]
  (defdb connection
    {:classname   "org.postgresql.Driver"
     :subprotocol "postgresql"
     :subname     (env :subname)
   ; Any additional keys are passed to the driver
   ; as driver-specific properties.
     :user        (env :username)
     :password    (env :password)}))

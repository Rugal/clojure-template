(ns ga.rugal.clojure.controller.registration
  "namespace for registration controller"
  (:require [ga.rugal.clojure.core.service.registration :as r]
            [compojure.core :refer :all]
            [compojure.coercions :refer [as-int]])
  (:import (clojure.lang ExceptionInfo)))

(defn- get [id]
  (if-let [bean (r/get id)]
    {:status 200 :body bean}
    {:status 404}))

(defn- save [bean]
  (try
    (if-let [b (r/save bean)]
      {:status 201 :body b}
      {:status 400})
    (catch ExceptionInfo e {:status (-> e ex-data :status)}))
  )

(defn- delete [id]
  (if (r/get id)
    (let [_ (r/delete id)]
      {:status 204})
    {:status 404}))

(defn- update [id bean]
  (try
    (if (r/update (assoc bean :id id))
      {:status 200}
      {:status 400})
    (catch ExceptionInfo e {:status (-> e ex-data :status)}))
  )

(def controller
  (context "/registration" []
    (context "/:id" [id :<< as-int]
      (GET "/" [] (get id))
      (PUT "/" [:as request] (let [bm (:body request)] (update id bm)))
      (DELETE "/" [] (delete id)))
    (POST "/" [:as request] (let [bm (:body request)] (save bm)))))

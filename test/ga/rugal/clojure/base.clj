(ns ga.rugal.clojure.base
  (:require [ga.rugal.clojure.main :refer [router]]))

(defn request [resource method params & body]
  (router {:request-method method
           :uri         resource
           :params      params
           :body        (first body)}))

(def student-bean {:name "Rugal" :age 23})
(def course-bean {:name "Math"})
(def registration-bean {:score 100})

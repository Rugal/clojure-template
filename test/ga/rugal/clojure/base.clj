(ns ga.rugal.clojure.base)

(defn request [web-app resource method params & body]
  (web-app {:request-method method
            :uri            resource
            :params         params
            :body           (first body)}))

(def student-bean {:name "Rugal" :age 23})
(def course-bean {:name "Math"})
(def registration-bean {:score 100})

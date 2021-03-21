(ns ga.rugal.clojure.core.dao.student-test
  "test namespace for student dao"
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.base :as b]
            [ga.rugal.clojure.core.dao.student :as student]))

(def ^:dynamic *bean* b/student-bean)

(defn- wrap-setup [f]
  (binding [*bean* (student/save *bean*)]
    (f)
    (student/delete (:id *bean*))))
(use-fixtures :each wrap-setup)

(deftest update
  (testing "update"
    (let [row (student/update *bean*)]
      (is (= 1 row)))))

(deftest get
  (testing "get by id"
    (let [row (student/get (:id *bean*))]
      (is (= *bean* row)))))

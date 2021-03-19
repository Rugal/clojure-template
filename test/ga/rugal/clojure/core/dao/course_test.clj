(ns ga.rugal.clojure.core.dao.course-test
  "test namespace for course dao"
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.base :as b]
            [ga.rugal.clojure.core.dao.course :refer :all]))

(def ^:dynamic *bean* b/course-bean)

(defn- wrap-setup [f]
  (binding [*bean* (save *bean*)]
    (f)
    (delete (:id *bean*))))
(use-fixtures :each wrap-setup)

(deftest test-update
  (testing "update"
    (let [row (update *bean*)]
      (is (= 1 row)))))

(deftest test-get-by-id
  (testing "get by id"
    (is (not (empty? *bean*)))))

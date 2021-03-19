(ns ga.rugal.clojure.core.dao.student-test
  "test namespace for student dao"
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.base :as b]
            [ga.rugal.clojure.core.dao.student :refer :all]))

(def ^:dynamic *bean* b/student-bean)

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

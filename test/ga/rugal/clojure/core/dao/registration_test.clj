(ns ga.rugal.clojure.core.dao.registration-test
  "test namespace for registration dao"
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.core.dao.registration :refer :all]))

(def ^:dynamic *bean* {:score 23})

(defn- wrap-setup [f]
  (binding [*bean* (save *bean*)]
    (f)
    (delete (:rid *bean*))))
(use-fixtures :each wrap-setup)

(deftest test-update
  (testing "update"
    (let [row (update *bean*)]
      (is (= 1 row)))))

(deftest test-get-by-id
  (testing "get by id"
    (is (not (empty? *bean*)))))

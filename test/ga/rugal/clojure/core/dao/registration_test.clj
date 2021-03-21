(ns ga.rugal.clojure.core.dao.registration-test
  "test namespace for registration dao"
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.core.dao.registration :as registration]))

(def ^:dynamic *bean* {:score 23})

(defn- wrap-setup [f]
  (binding [*bean* (registration/save *bean*)]
    (f)
    (registration/delete (:rid *bean*))))
(use-fixtures :each wrap-setup)

(deftest update
  (testing "update"
    (let [row (registration/update *bean*)]
      (is (= 1 row)))))

(deftest get-by-id
  (testing "get by id"
    (let [row (registration/get (:id *bean*))]
      (is (= *bean* row)))))

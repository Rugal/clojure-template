(ns ga.rugal.clojure.core.service.registration-test
  "unit test for registration service"
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.core.service.registration :as service]
            [ga.rugal.clojure.core.dao.registration :as dao]
            [ga.rugal.clojure.core.service.student :as s]
            [ga.rugal.clojure.core.service.course :as c]))

(deftest get
  (let [course {:id 0 :name "test" :age 0}
        student {:id 0 :name "test"}
        bean {:id 0 :student student :course course :score 0}]
    (with-redefs [s/get (fn [id] student)
                  c/get (fn [id] course)]
      (with-redefs [dao/get (fn [id] bean)]
        (testing "with parameter"
          (is (= (service/get 1) bean))))
      (with-redefs [dao/get (fn [id] nil)]
        (testing "without parameter"
          (is (= (service/get 1) nil))))
      )
    )
  )


(deftest save
  (let [bean {:id 0 :student_id 0 :course_id 0 :score 0}]
    (with-redefs [dao/save (fn [b] bean)]
      (testing "with all"
        (is (= (service/save bean) bean)))
      (testing "without :student_id"
        (is (= (service/save (dissoc bean :student_id)) nil)))
      (testing "without :course_id"
        (is (= (service/save (dissoc bean :course_id)) nil)))
      (testing "without :score"
        (is (= (service/save (dissoc bean :score)) nil)))
      )
    )
  )

(deftest update
  (let [bean {:id 0 :student_id 0 :course_id 0 :score 0}]
    (with-redefs [dao/update (fn [b] 1)]
      (testing "with all"
        (is (= (service/update bean) 1)))
      (testing "without :student_id"
        (is (= (service/update (dissoc bean :student_id)) nil)))
      (testing "without :course_id"
        (is (= (service/update (dissoc bean :course_id)) nil)))
      (testing "without :score"
        (is (= (service/update (dissoc bean :score)) nil)))
      (testing "without :id"
        (is (= (service/update (dissoc bean :id)) nil)))
      )
    )
  )

(deftest delete
  (with-redefs [dao/delete (fn [b] 1)]
    (testing "with parameter"
      (is (= (service/delete 0) 1)))
    (testing "without parameter"
      (is (= (service/delete nil) nil)))
    )
  )

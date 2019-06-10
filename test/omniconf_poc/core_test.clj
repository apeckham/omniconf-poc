(ns omniconf-poc.core-test
  (:require [clojure.test :refer :all]
            [omniconf-poc.core :refer :all]
            [omniconf.core :as cfg]
            [omniconf-poc.commands :refer [connect disconnect]])
  (import clojure.lang.ExceptionInfo))

(defmacro with-cfg [values & body]
  `(with-redefs [cfg/config-values (atom (sorted-map))
                 cfg/logging-fn (atom :noop)]
    (cfg/populate-from-map ~values)
    (cfg/verify :silent true)
    ~@body))

(deftest connect-test
  (with-cfg {:command :connect :hostname "asdf"}
    (is (= "Connecting to asdf!\n" (with-out-str (connect))))))

(deftest disconnect-test
  (with-cfg {:command :disconnect :confirm false}
    (is (= "Disconnecting! (confirm is false)\n" (with-out-str (disconnect))))))

(deftest invalid-values-test
  (is (thrown-with-msg? ExceptionInfo #"must be one of" (with-cfg {:command :foobar}))))
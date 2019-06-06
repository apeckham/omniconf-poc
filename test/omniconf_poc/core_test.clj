(ns omniconf-poc.core-test
  (:require [clojure.test :refer :all]
            [omniconf-poc.core :refer :all]
            [omniconf.core :as cfg]
            [omniconf-poc.config :refer [define-cfg]]
            [omniconf-poc.commands :refer [connect]]))

(defn setup-cfg [command values]
  (define-cfg command)
  (cfg/populate-from-map values)
  (cfg/verify :silent true))

(deftest connect-test
  (setup-cfg :connect {:hostname "asdf"})
  (is (= "Connecting to asdf!\n" (with-out-str (connect)))))

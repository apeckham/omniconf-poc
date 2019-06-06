(ns omniconf-poc.core-test
  (:require [clojure.test :refer :all]
            [omniconf-poc.core :refer :all]
            [omniconf.core :as cfg]
            [omniconf-poc.config :refer [define-cfg]]
            [omniconf-poc.commands :refer [connect disconnect]]))

(defn setup-cfg [command values]
  (define-cfg command)
  (cfg/populate-from-map values)
  (cfg/verify :silent true))

(deftest connect-test
  (setup-cfg :connect {:hostname "asdf"})
  (is (= "Connecting to asdf!\n" (with-out-str (connect)))))

(deftest disconnect-test
  (setup-cfg :connect {:hostname "asdf"})
  (cfg/set :confirm false)
  (is (= "Disconnecting! (confirm is false)\n" (with-out-str (disconnect)))))

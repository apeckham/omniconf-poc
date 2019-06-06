(ns omniconf-poc.core
  (:gen-class)
  (:require [omniconf.core :as cfg]
            [omniconf-poc.config :refer [define-cfg]]
            omniconf-poc.commands))

(defn -main
  [command & args]
  (define-cfg (keyword command))
  (cfg/populate-from-cmd (or args ["--help"]))
  (cfg/populate-from-env)
  (cfg/verify)
  ((ns-resolve 'omniconf-poc.commands (symbol command))))

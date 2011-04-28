(ns ru.nol.qbook.greet.ClojureGreeter
	(:gen-class
	   :name "ru.nol.qbook.greet.ClojureGreeter"
	   :implements [ru.nol.qbook.greet.Greeter]
	 )
  ( :use ru.nol.qbook.greet.ClGreetings )
)
(defn -getGreeting [this] (getSomeGreeting))
(defn -getProposal [this] "Why so lispy?")



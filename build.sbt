name := "socialprinter"

version := "1.0"

lazy val `socialprinter` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( 
		javaJdbc,
		javaEbean,
		cache,
		javaWs,
		"mysql" % "mysql-connector-java" % "5.1.33",
		"org.webjars" %% "webjars-play" % "2.3.0-2",
  		"org.webjars" % "bootstrap" % "3.1.1-2"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  
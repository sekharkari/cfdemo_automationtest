node {
   // Mark the code checkout 'stage'....
   stage 'Checkout'

   // Checkout code from repository
   checkout scm

   // Get the maven tool.
   // ** NOTE: This 'M3' maven tool must be configured
   // **       in the global configuration.
   def mvnHome = tool 'M3'

   // Mark the code build 'stage'....
   stage 'Build'
   sh "${mvnHome}/bin/mvn test"
   step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
   
}
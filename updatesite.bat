SET HOME_STUDIO=C:\Travail\ISDesigner-2.0.0\
SET HOME_UPDATESITE_P2_METADATA=C:\Travail\updateSiteDeltaPack-2.0.0
SET HOME_UPDATESITE_P2_ARTIFACT=C:\Travail\updateSiteDeltaPack-2.0.0
SET VERSION_STUDIO=2.0.0
%HOME_STUDIO%is-designer.exe -consolelog -nosplash -verbose -application org.eclipse.equinox.p2.publisher.FeaturesAndBundlesPublisher -metadataRepository file:%HOME_UPDATESITE_P2_METADATA%-%VERSION_STUDIO% -artifactRepository file:%HOME_UPDATESITE_P2_ARTIFACT%-%VERSION_STUDIO% -source %HOME_STUDIO% -publishArtifacts
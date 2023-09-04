package com.alineson.infra.ops.telemetry.receiver.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 引处引用aliyun-sls的参数配置参考
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class Constants {

    public static final String MQ_METRICS_TOPIC = "alinesno-ops-telemetry-metrics" ;
    public static final String MQ_LOG_TOPIC = "alinesno-ops-telemetry-logs" ;
    public static final String MQ_TRACE_TOPIC = "alinesno-ops-telemetry-trace" ;


    public static final String AttributeCloudAccount = "cloud.account.id";
    public static final String AttributeCloudProvider = "cloud.provider";
    public static final String AttributeCloudRegion = "cloud.region";
    public static final String AttributeCloudZone = "cloud.zone";
    public static final String AttributeCloudInfrastructureService = "cloud.infrastructure_service";
    public static final String AttributeContainerID = "container.id";
    public static final String AttributeContainerImage = "container.image.name";
    public static final String AttributeContainerName = "container.name";
    public static final String AttributeContainerTag = "container.image.tag";
    public static final String AttributeDeploymentEnvironment = "deployment.environment";
    public static final String AttributeFaasID = "faas.id";
    public static final String AttributeFaasInstance = "faas.instance";
    public static final String AttributeFaasName = "faas.name";
    public static final String AttributeFaasVersion = "faas.version";
    public static final String AttributeHostID = "host.id";
    public static final String AttributeHostImageID = "host.image.id";
    public static final String AttributeHostImageName = "host.image.name";
    public static final String AttributeHostImageVersion = "host.image.version";
    public static final String AttributeHostName = "host.name";
    public static final String AttributeHostType = "host.type";
    public static final String AttributeK8sCluster = "k8s.cluster.name";
    public static final String AttributeK8sContainer = "k8s.container.name";
    public static final String AttributeK8sCronJob = "k8s.cronjob.name";
    public static final String AttributeK8sCronJobUID = "k8s.cronjob.uid";
    public static final String AttributeK8sDaemonSet = "k8s.daemonset.name";
    public static final String AttributeK8sDaemonSetUID = "k8s.daemonset.uid";
    public static final String AttributeK8sDeployment = "k8s.deployment.name";
    public static final String AttributeK8sDeploymentUID = "k8s.deployment.uid";
    public static final String AttributeK8sJob = "k8s.job.name";
    public static final String AttributeK8sJobUID = "k8s.job.uid";
    public static final String AttributeK8sNamespace = "k8s.namespace.name";
    public static final String AttributeK8sNodeName = "k8s.node.name";
    public static final String AttributeK8sNodeUID = "k8s.node.uid";
    public static final String AttributeK8sPod = "k8s.pod.name";
    public static final String AttributeK8sPodUID = "k8s.pod.uid";
    public static final String AttributeK8sReplicaSet = "k8s.replicaset.name";
    public static final String AttributeK8sReplicaSetUID = "k8s.replicaset.uid";
    public static final String AttributeK8sStatefulSet = "k8s.statefulset.name";
    public static final String AttributeK8sStatefulSetUID = "k8s.statefulset.uid";
    public static final String AttributeOSType = "os.type";
    public static final String AttributeOSDescription = "os.description";
    public static final String AttributeProcessCommand = "process.command";
    public static final String AttributeProcessCommandLine = "process.command_line";
    public static final String AttributeProcessExecutableName = "process.executable.name";
    public static final String AttributeProcessExecutablePath = "process.executable.path";
    public static final String AttributeProcessID = "process.pid";
    public static final String AttributeProcessOwner = "process.owner";
    public static final String AttributeServiceInstance = "service.instance.id";
    public static final String AttributeServiceName = "service.name";
    public static final String AttributeServiceNamespace = "service.namespace";
    public static final String AttributeServiceVersion = "service.version";
    public static final String AttributeTelemetryAutoVersion = "telemetry.auto.version";
    public static final String AttributeTelemetrySDKLanguage = "telemetry.sdk.language";
    public static final String AttributeTelemetrySDKName = "telemetry.sdk.name";
    public static final String AttributeTelemetrySDKVersion = "telemetry.sdk.version";

    public static List<String> getResourceSemanticConventionAttributeNames() {
        return Arrays.asList(
                AttributeCloudAccount,
                AttributeCloudProvider,
                AttributeCloudRegion,
                AttributeCloudZone,
                AttributeCloudInfrastructureService,
                AttributeContainerID,
                AttributeContainerImage,
                AttributeContainerName,
                AttributeContainerTag,
                AttributeDeploymentEnvironment,
                AttributeFaasID,
                AttributeFaasInstance,
                AttributeFaasName,
                AttributeFaasVersion,
                AttributeHostID,
                AttributeHostImageID,
                AttributeHostImageName,
                AttributeHostImageVersion,
                AttributeHostName,
                AttributeHostType,
                AttributeK8sCluster,
                AttributeK8sContainer,
                AttributeK8sCronJob,
                AttributeK8sCronJobUID,
                AttributeK8sDaemonSet,
                AttributeK8sDaemonSetUID,
                AttributeK8sDeployment,
                AttributeK8sDeploymentUID,
                AttributeK8sJob,
                AttributeK8sJobUID,
                AttributeK8sNamespace,
                AttributeK8sNodeName,
                AttributeK8sNodeUID,
                AttributeK8sPod,
                AttributeK8sPodUID,
                AttributeK8sReplicaSet,
                AttributeK8sReplicaSetUID,
                AttributeK8sStatefulSet,
                AttributeK8sStatefulSetUID,
                AttributeOSType,
                AttributeOSDescription,
                AttributeProcessCommand,
                AttributeProcessCommandLine,
                AttributeProcessExecutableName,
                AttributeProcessExecutablePath,
                AttributeProcessID,
                AttributeProcessOwner,
                AttributeServiceInstance,
                AttributeServiceName,
                AttributeServiceNamespace,
                AttributeServiceVersion,
                AttributeTelemetryAutoVersion,
                AttributeTelemetrySDKLanguage,
                AttributeTelemetrySDKName,
                AttributeTelemetrySDKVersion
        );
    }
}

@Library("projetas-library") _

PROJECT_NAME = "java-car-service"
PROJECT_VERSION = "${env.BRANCH_NAME}:${BUILD_ID}"

def config = [
        activeBranches: ['dev', 'qa', 'master'],
        name          : 'Project Name',
        image         : PROJECT_NAME,
        stack         : PROJECT_NAME,
        buildExtras   : '',
        key           : "${PROJECT_NAME}:${PROJECT_VERSION}",
        version       : PROJECT_VERSION,
        branch        : env.BRANCH_NAME,
        language      : 'java',
        sonarExtras   : '',
        testExtras    : '-Denforcer.skip=true',
        swarms        : [
                dev   : 'node01.docker.projetas.com.br',
                qa    : 'node01.docker.projetas.com.br',
                master: 'node01.docker.projetas.com.br',
        ]
]

javaPipeline(config)

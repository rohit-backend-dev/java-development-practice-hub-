# DevOps Syllabus for Java Spring Boot Developers (2025 Edition)

---

## 🧭 Overview

A 12-week, hands-on DevOps program for Java backend engineers, focused on real-world skills and project-based learning. Each week contains clear topics, tools, and learning outcomes.

---

### Week 1 – Linux & Shell Scripting

**Session 1: Red Hat Linux Administration**
- Linux OS architecture, CLI navigation
- File system structure (/, /etc, /var, /home)
- User/group management (`adduser`, `usermod`, `passwd`)
- Package management (`yum`, `rpm`)
- Service management (`systemctl`, `chkconfig`)
- Network config, SNAT/DNAT, firewall, CIDR, netmask

**Session 2: Shell Scripting**
- Shell syntax, variables, operators
- Conditionals, loops, functions
- Automate tasks: user creation, monitoring, backups

**Outcome:** Confident Linux user and shell scripter, able to automate sysadmin tasks.

---

### Week 2 – Cloud Services & AWS Fundamentals

**Session 3: AWS Essentials**
- Regions, Availability Zones, Edge Locations
- Launch/configure EC2, use SSH/Key Pairs
- EBS volumes, security groups, user-data scripts

**Session 4: AWS Core Services**
- S3 (buckets, policies, lifecycle)
- ELB, Auto Scaling
- CloudFormation (IaC basics)
- CloudWatch (logs, metrics, billing alarms)
- IAM (roles, policies, least privilege)
- Lambda, SNS, SQS, ECS, ECR

**Outcome:** Deploy and manage basic cloud resources, understand AWS core services.

---

### Week 3 – Source Code Management & Networking

**Session 5: Git Deep Dive**
- CVCS vs DVCS
- Git CLI: clone, commit, push, pull, merge, stash, rebase, cherry-pick, squash, fork, pull requests
- Conflict resolution, reset vs revert

**Session 6: GitHub & Networking**
- GitHub SSH/HTTPS authentication
- Team workflows, PRs, repo management
- OSI Model, Subnetting, IP addressing, DNS, DHCP
- Routers, switches, LAN/WAN

**Outcome:** Master Git & GitHub workflows, understand networking essentials for DevOps.

---

### Week 4 – Docker & SonarQube

**Session 7: Docker Fundamentals**
- Virtualization vs containerization
- Docker architecture: client, daemon, host
- Dockerfile authoring, image/layer management
- Container lifecycle, networking, Compose
- Volumes, persistent data, Docker security

**Session 8: SonarQube for Code Quality**
- Code analysis, quality gates
- SonarLint, Sonar Scanner
- CI integration with SonarQube

**Outcome:** Build, run, and secure containers; enforce code quality in CI/CD.

---

### Week 5 – Minor Project

- Automate Linux admin tasks with Shell scripts
- Use Git/GitHub for version control
- Containerize with Docker
- Document using Markdown

**Outcome:** Deploy a small automated solution using DevOps fundamentals.

---

### Week 6 – Jenkins & CI/CD

**Session 11: Jenkins Foundations**
- Jenkins installation, setup
- Freestyle jobs, pipelines (Jenkinsfile, DSL)
- SCM polling, webhook triggers
- GitHub + Jenkins integration

**Session 12: Advanced Jenkins**
- Master-slave setup, dynamic Docker slaves
- Groovy scripting in pipelines
- Jenkins plugins (Docker, Maven, Git)
- Deploy to Kubernetes via Jenkins

**Outcome:** Automate build, test, deploy pipelines with Jenkins.

---

### Week 7 – Kubernetes Basics

**Session 13: Kubernetes Architecture**
- Nodes, pods, containers
- Master components (API server, scheduler, etcd)
- `kubectl` basics, minikube setup

**Session 14: Core K8s Concepts**
- Pod creation, labels/selectors
- Services (ClusterIP, NodePort, LoadBalancer)
- Namespaces, multi-container pods
- YAML specs usage

**Outcome:** Deploy/manage basic workloads on Kubernetes.

---

### Week 8 – Advanced Kubernetes & App Deployment

**Session 15: Microservices on K8s**
- Deploy Spring Boot via Deployments/Services
- ConfigMaps, Secrets
- Persistent Volumes, probes
- Auto-scaling, self-healing

**Session 16: Helm & Istio**
- Helm: charts, templates
- Istio: service mesh, RBAC, secure comms
- HPA, Ingress, taints, tolerations

**Outcome:** Deploy, secure, and scale microservices on Kubernetes.

---

### Week 9 – Ansible Automation

**Session 17: Ansible Basics**
- Setup, architecture, ad-hoc commands
- Inventory, playbooks, roles

**Session 18: Advanced Ansible**
- Deploy Apache via playbook
- Multi-node K8s cluster setup
- Variables, facts, handlers, tags, templates

**Outcome:** Automate configuration management and deployments with Ansible.

---

### Week 10 – Infrastructure as Code with Terraform

**Session 19: Terraform Essentials**
- What is IaC, introduction to Terraform
- Terraform CLI, variables, resources, dependencies
- State management, remote backend

**Session 20: Advanced Terraform**
- Modules, workspaces
- Create VPC, subnets, EC2, IAM via Terraform
- Locking, lifecycle, provisioners
- CIDR example, secure infra

**Outcome:** Provision, manage, and secure cloud infrastructure as code.

---

### Week 11 – Monitoring (Prometheus & Grafana)

**Session 21: Grafana**
- Install/setup Grafana
- Connect to MySQL, create dashboards

**Session 22: Prometheus**
- Install/setup Prometheus
- Scrape K8s metrics, setup alerts
- Integrate with Grafana

**Outcome:** Monitor microservices and infrastructure with real-time dashboards and alerts.

---

### Week 12 – Final Projects

**Mentor-Guided Projects**
- Deploy microservices on ECS/EKS
- Integrate CloudWatch, load balancers, SSL

**Self-Guided Projects**
- GitHub Action: self-hosted runner
- Expose container with NGINX
- Shell script for resource monitoring and alerting

---

## 🛠️ Toolkit Summary

| Area                | Tools/Tech                                 |
|---------------------|--------------------------------------------|
| OS & Automation     | RedHat, Bash, Shell Scripting              |
| Version Control     | Git, GitHub, GitLab                        |
| CI/CD               | Jenkins, GitHub Actions                    |
| Containers          | Docker, Docker Compose                     |
| Orchestration       | Kubernetes, Helm, Istio                    |
| Monitoring          | Prometheus, Grafana, CloudWatch            |
| Infra as Code       | Terraform, CloudFormation, Ansible         |
| Cloud Services      | AWS EC2, S3, RDS, ECS, EKS, IAM, Lambda    |
| Security & QA       | SonarQube, RBAC, Cert-Manager, Vault       |
| Scripting           | Shell, YAML, JSON, Groovy, Terraform HCL   |

---

## 📚 Resources

- [AWS Docs](https://docs.aws.amazon.com/)
- [Kubernetes Docs](https://kubernetes.io/docs/)
- [Jenkins Pipeline](https://www.jenkins.io/doc/book/pipeline/)
- [Docker Docs](https://docs.docker.com/)
- [Terraform Docs](https://developer.hashicorp.com/terraform/docs)
- [Prometheus](https://prometheus.io/docs/)
- [Grafana](https://grafana.com/docs/)
- [SonarQube](https://docs.sonarqube.org/)

---

## ⚡ Capstone Project

- Build, containerize, and deploy a Java Spring Boot microservice application.
- CI/CD with GitHub Actions/Jenkins.
- Kubernetes deployment (with Helm/Istio if advanced).
- Infra provisioning via Terraform.
- Cloud hosting (AWS ECS/EKS).
- Monitoring with Prometheus/Grafana.
- Documentation and handover.

---

## ✅ Tracking & Extra Information

### Learning Outcomes Breakdown

- **Linux & Scripting:** Navigate and automate daily sysadmin/dev workflows.
- **Cloud & AWS:** Confidently launch, secure, and monitor cloud resources.
- **Source Control & Networking:** Team collaboration, code review, and networking for cloud-native apps.
- **Containers & Quality:** Build, scan, and ship production-ready containers.
- **CI/CD:** Automate software lifecycle from code to prod.
- **Kubernetes:** Deploy, expose, and scale microservices.
- **IaC & Automation:** Reproducible infra with automation.
- **Monitoring:** Real-time insight, alerting, and visualization.
- **Security:** Implement best practices (IAM, RBAC, least privilege, code scanning).
- **Documentation:** Markdown, diagrams, and handover guides.

### Project & Portfolio Deliverables

- **Minor Project:** Automated admin tasks, containerization, VCS, and documentation.
- **Final Capstone:** End-to-end DevOps pipeline for Spring Boot microservices.
- **Artifacts:** Jenkins pipelines, Helm charts, Terraform configs, monitoring setups, shell scripts, and project docs (README, diagrams, tracking sheets).

---

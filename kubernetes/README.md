Note: Kubernetes deployment is not working because there is a communication problem between mysql pod 
and application pod. Application could not access to mysql pod in Kubernetes environment. This is a 
TODO for a future work. 

https://github.com/yankils/Simple-DevOps-Project/blob/master/Kubernetes/Kubernetes-setup.MD





kops create cluster --cloud=aws --zones=us-east-1a --name=my-finance.k8s.ozdamar.com --dns-zone=ozdamar.com --dns private
Cluster configuration has been created.
Suggestions:
 * list clusters with: kops get cluster
 * edit this cluster with: kops edit cluster my-finance.k8s.ozdamar.com
 * edit your node instance group: kops edit ig --name=my-finance.k8s.ozdamar.com nodes
 * edit your master instance group: kops edit ig --name=my-finance.k8s.ozdamar.com master-us-east-1a
Finally configure your cluster with: kops update cluster --name my-finance.k8s.ozdamar.com --yes


Cluster is starting.  It should be ready in a few minutes.

Suggestions:
 * validate cluster: kops validate cluster --wait 10m
 * list nodes: kubectl get nodes --show-labels
 * ssh to the master: ssh -i ~/.ssh/id_rsa ubuntu@api.my-finance.k8s.ozdamar.com

ssh -i ~/.ssh/id_rsa ubuntu@api.my-finance.k8s.ozdamar.com



Check Logs of pod in Kubetctl: 
kubectl logs --follow ozdamar-my-finance-deployment-959c69556-7h9hk -c mysql-standalone
kubectl logs --follow ozdamar-my-finance-deployment-959c69556-7h9hk -c ozdamar-my-finance
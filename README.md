# Human Resource System (Spring Boot + React)

A full-stack Human Resource Management System customized with a Spring Boot backend and a React (TypeScript + Tailwind CSS) frontend.

## Features
- **Backend**: Spring Boot 3, Spring Data JPA, MySQL.
- **Frontend**: React 18, TypeScript, Tailwind CSS, Vite.
- **Infrastructure**: Docker Compose (Local), Helm & ArgoCD (Kubernetes).

---

## 🚀 Local Development (Docker Compose)

The easiest way to run the application locally is using Docker Compose.

### Prerequisites
- Docker & Docker Compose installed.

### Steps
1.  **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd <project-directory>
    ```

2.  **Start the application**:
    ```bash
    docker-compose up --build
    ```

3.  **Access the App**:
    - **Frontend (UI)**: [http://localhost:5173](http://localhost:5173)
    - **Backend API**: [http://localhost:4049/api/employees](http://localhost:4049/api/employees)

---

## ☁️ Kubernetes Deployment (GKE + ArgoCD)

This guide covers how to push your images to a registry and deploy the full stack to your GKE cluster using ArgoCD.

### Prerequisites
- **GKE Cluster** is running and `kubectl` is configured.
- **DockerHub** account (or Google Artifact Registry).
- **Helm** installed locally (optional, for linting).

### 1. Build and Push Docker Images
You must build the images and push them to a registry so Kubernetes can download them.

> [!IMPORTANT]
> Replace `your-dockerhub-username` with your actual Docker Hub username (e.g., `zaikbros`).

#### Backend
```powershell
# Login to Docker Hub
docker login

# Build
docker build -t your-dockerhub-username/hr-backend:v1 .

# Push
docker push your-dockerhub-username/hr-backend:v1
```

#### Frontend
```powershell
cd frontend
# Build
docker build -t your-dockerhub-username/hr-frontend:v2 .

# Push
docker push your-dockerhub-username/hr-frontend:v2
cd ..
```

### 2. Update Helm Configuration
Open `helm/hr-system/values.yaml` and update the `image.repository` and `tag` fields:

```yaml
backend:
  image:
    repository: your-dockerhub-username/hr-backend
    tag: v1  # Ensure this matches the pushed tag
frontend:
  image:
    repository: your-dockerhub-username/hr-frontend
    tag: v2  # Ensure this matches the pushed tag
```

**Push these changes to your Git repository** so ArgoCD can detect them.

### 3. Install ArgoCD (If not installed)
If you haven't installed ArgoCD on your cluster yet:

```powershell
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
```
### Or

```powershell
helm repo add argo https://argoproj.github.io/argo-helm
helm repo update 
helm install argocd argo/argo-cd --namespace argocd --create-namespace
```

To access the UI (optional):
```powershell
kubectl port-forward svc/argocd-server -n argocd 8080:443
```
(Login with `admin` and the initial password found in the `argocd-initial-admin-secret`).

#### Get the initial password
```powershell
kubectl get secret -n argocd argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d
```


### Install the cert-manager (Optional)
Uncomment these helm/templates/certificates.yaml and helm/templates/cluster-issuer.yaml

```git bash
helm repo add jetstack https://charts.jetstack.io
helm repo update
helm install cert-manager jetstack/cert-manager   --namespace cert-manager   --create-namespace   --set installCRDs=true
```
<!-- ```git bash
openssl req -x509 -nodes -days 365 \
  -newkey rsa:2048 \
  -keyout key.pem \
  -out cert.pem \
  -subj "//CN=hr.local" 
```
#### Check the output of the above command
```git bash
ls *.pem
```
Output:
cert.pem  key.pem
-->

### Install the ingress-nginx
```powershell
kubectl apply -f argocd/ingress-nginx.yaml
```

### 4. Deploy Application via ArgoCD
Apply the Application manifest which tells ArgoCD to watch your repo and deploy the Helm chart:

```powershell
kubectl apply -f argocd/application.yaml
```

### 5. Verify Deployment
Check if the pods are running in the `hr-system` namespace:

```powershell
kubectl get pods -n hr-system
```

Get the external IP of the frontend service:
```powershell
kubectl get svc -n hr-system
```
Access the application using the `EXTERNAL-IP` (Port 80).

---

## 📊 Observability (Prometheus & Grafana)

The system includes a fully configured monitoring stack using `kube-prometheus-stack`.

### 1. Install Monitoring Stack
Deploy the monitoring infrastructure via ArgoCD:

```powershell
kubectl apply -f argocd/monitoring.yaml
```

### 2. Access Grafana
Once the pods in the `monitoring` namespace are ready, access Grafana:

```powershell
# Port forward Grafana
kubectl port-forward svc/kube-prometheus-stack-grafana 8080:80 -n monitoring
```
URL: [http://localhost:8080](http://localhost:8080)

**Credentials**:
*   **User**: `admin`
*   **Password**: `prom-operator`

### 3. Import Spring Boot Dashboard
To view application metrics:
1.  Go to **Dashboards -> Import**.
2.  Enter Dashboard ID: **4701** (Spring Boot Statistics).
3.  Select the **Prometheus** data source.
4.  Click **Import**.

You will now see metrics for CPU, Memory, and HTTP Requests from your backend!

### 4. Import k8s Dashboard
To view application metrics:
1.  Go to **Dashboards -> Import**.
2.  Enter Dashboard ID: **15661** (k8s cluster monitoring).
3.  Select the **Prometheus** data source.
4.  Click **Import**.

You will now see metrics for kubernetes cluster!

package com.example.shirodemo.test.listCompare;

public class Node {

    private String providerId;
    private String contentId;
    private String nodeId;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (!providerId.equals(node.providerId)) return false;
        if (!contentId.equals(node.contentId)) return false;
        return nodeId.equals(node.nodeId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contentId == null) ? 0 : contentId.hashCode());
        result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
        result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
        return result;
    }
}

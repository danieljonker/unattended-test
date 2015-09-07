package nz.co.jonker.skyrewards.data.models;

/**
 * Created by jonker on 6/09/15.
 */
public class Eligibility {
    int pointsRequired; //assuming some sort of points system would be involved?
    int pointsGathered;

    public Eligibility() {
    }

    public int getPointsReq() {
        return pointsRequired;
    }

    public void setPointsReq(int pointsReq) {
        this.pointsRequired = pointsReq;
    }

    public int getPointsGathered() {
        return pointsGathered;
    }

    public void setPointsGathered(int pointsGathered) {
        this.pointsGathered = pointsGathered;
    }
}
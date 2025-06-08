package guild.decorator;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;

public class BackupDecorator extends BountyHunterDecorator {

    public BackupDecorator(BountyHunter hunter) {
        super(hunter);
    }

    @Override
    public void track(Criminal target) {
        System.out.println("[BACKUP] Coordinating with support team - perimeter established");
        super.track(target);
        System.out.println("[BACKUP] All escape routes covered by backup units");
    }

    @Override
    public void capture(Criminal target) {
        System.out.println("[BACKUP] Moving in with tactical support team");
        super.capture(target);
        System.out.println("[BACKUP] Target apprehended with full team coordination");
    }

    @Override
    public void reportStatus() {
        System.out.println("[BACKUP] Full tactical team standing by - all units ready");
        super.reportStatus();
    }
}

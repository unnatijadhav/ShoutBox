import { TestBed } from '@angular/core/testing';

import { UserDashboardService } from './user-dashboard.service';

describe('UserDashboardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserDashboardService = TestBed.get(UserDashboardService);
    expect(service).toBeTruthy();
  });
});

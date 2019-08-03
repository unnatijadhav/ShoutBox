import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorOtherComponent } from './error-other.component';

describe('ErrorOtherComponent', () => {
  let component: ErrorOtherComponent;
  let fixture: ComponentFixture<ErrorOtherComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ErrorOtherComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ErrorOtherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

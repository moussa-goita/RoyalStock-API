import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BonEntreDetailPage } from './bon-entre-detail.page';

describe('BonEntreDetailPage', () => {
  let component: BonEntreDetailPage;
  let fixture: ComponentFixture<BonEntreDetailPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(BonEntreDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

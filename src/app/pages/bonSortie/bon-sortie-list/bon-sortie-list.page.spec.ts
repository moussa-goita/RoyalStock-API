import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BonSortieListPage } from './bon-sortie-list.page';

describe('BonSortieListPage', () => {
  let component: BonSortieListPage;
  let fixture: ComponentFixture<BonSortieListPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(BonSortieListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
